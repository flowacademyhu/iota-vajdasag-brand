import "./App.css";
import Registration from "./pages/Registration";
import Login from "./pages/Login";
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
        <SwitchLanguage />
        {/* {isLoggedIn ? <Menu /> : <Redirect to="/login" />} */}
        {loggedInAsCompanyAdmin && <Redirect to="/company-admin" />}
        {loggedInAsSuperAdmin && <Redirect to="/super-admin" />}
      </div>
      <div>
        <Switch>
          <Route path="/registration">
            <Registration />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/company-admin">
            <div>{t("Companyadmin")}</div>
          </Route>
          <Route path="/super-admin">
            <div>{t("Superadmin")}</div>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}
