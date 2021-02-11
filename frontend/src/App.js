import "./App.css";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import SwitchLanguage from "./components/SwitchLanguage";
import { useTranslation } from "react-i18next";
import Menu from "./components/Menu.js";

export default function App() {
  const { t } = useTranslation();
  const isLoggedIn = false;
  const loggedInAsSuperAdmin = false;
  const loggedInAsCompanyAdmin = false;

  return (
    <Router>
      <div>
        {isLoggedIn ? <Menu /> : <Redirect to="/registration" />}
        {loggedInAsCompanyAdmin && <Redirect to="/company-admin" />}
        {loggedInAsSuperAdmin && <Redirect to="/super-admin" />}
      </div>
      <div>
        <Switch>
          <Route path="/registration">
            <SwitchLanguage />
            <div>{t("registration")}</div>
          </Route>
          <Route path="/login">
            <div>Login</div>
          </Route>
          <Route path="/company-admin">
            <div id="page-content-wrapper">Company</div>
          </Route>
          <Route path="/super-admin">
            <div>Superadmin</div>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}