package hu.flowacademy.vajdasagbrand.configuration;

import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.http.HttpTransportOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import io.grpc.ManagedChannelBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
public class FirebaseConfiguration {

    @Value("${firestore.emulator.url:}")
    private String emulatorHost;
    @Value("${firestore.project_id}")
    private String projectId;
    @Value("${firestore.production.private_key_id}")
    private String privateKeyId;
    @Value("${firestore.production.private_key}")
    private String privateKey;
    @Value("${firestore.production.client_email}")
    private String clientEmail;
    @Value("${firestore.production.client_id}")
    private String clientId;
    @Value("${firestore.production.auth_uri}")
    private String authUri;
    @Value("${firestore.production.token_uri}")
    private String tokenUri;
    @Value("${firestore.production.auth_provider_x509_cert_url}")
    private String authProviderX509CertUrl;
    @Value("${firestore.production.client_x509_cert_url}")
    private String clientX509CertUrl;

    @Bean
    public Firestore firestore() {
        return Optional.ofNullable(emulatorHost).filter(s -> !s.isBlank())
                .map(s -> getEmulatorConfig())
                .orElseGet(() -> getProductionConfig());
    }
    @SneakyThrows
    private Firestore getProductionConfig() {
        return FirestoreClient.getFirestore(
                FirebaseApp.initializeApp(
                        FirebaseOptions.builder()
                                .setProjectId(projectId)
                                .setCredentials(ServiceAccountCredentials
                                        .fromPkcs8(
                                                clientId,
                                                clientEmail,
                                                privateKey.replaceAll("\\\\n", System.lineSeparator()),
                                                privateKeyId,
                                                null,
                                                new HttpTransportOptions.DefaultHttpTransportFactory(), // FIXME,
                                                new URI(tokenUri)
                                        ).toBuilder()
                                        .setProjectId(projectId)
                                        .build())
                                .build()
                )
        );
    }

    private Firestore getEmulatorConfig() {
        return FirestoreOptions.newBuilder()
                .setProjectId(projectId)
                .setChannelProvider(
                        InstantiatingGrpcChannelProvider.newBuilder()
                                .setEndpoint(emulatorHost)
                                .setChannelConfigurator(ManagedChannelBuilder::usePlaintext)
                                .build())
                .setCredentials(new Credentials() {
                    @Override
                    public String getAuthenticationType() {
                        throw new IllegalArgumentException("Not supported");
                    }
                    @Override
                    public Map<String, List<String>> getRequestMetadata(URI uri) throws IOException {
                        return Map.of("Authorization", List.of("Bearer owner"));
                    }
                    @Override
                    public boolean hasRequestMetadata() {
                        return true;
                    }
                    @Override
                    public boolean hasRequestMetadataOnly() {
                        return true;
                    }
                    @Override
                    public void refresh() {
                    }
                })
                .build().getService();
    }
}
