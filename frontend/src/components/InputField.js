import { Field, useField } from "formik";



const InputField = ({ label, ...props }) => {
    const [field, meta] = useField(props.name);
    const showError = meta.touched && meta.error;

    return (
        <>
            <label htmlFor={props.name}>{label}</label>
            <Field
                {...field}
                {...props}
                className={`form-control ${showError ? "is-invalid" : ""}`}
            ></Field>
            {showError && <div className="invalid-feedback">{meta.error}</div>}
        </>
    );


}

export default InputField