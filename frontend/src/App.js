import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import SwitchLanguage from "./components/SwitchLanguage";
import { useTranslation } from "react-i18next";

function App() {
  const { t } = useTranslation();

  return (
    <Router>
      <Switch>
        <Route path="/registration">
          <div>{t("registration")}</div>
          <SwitchLanguage />
        </Route>
        <Route path="/company-admin">
          <div>Company</div>
        </Route>
        <Route path="/super-admin">
          <div>Superadmin</div>
        </Route>
        <Route path="/login">
          <div>Login</div>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
