import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import SwitchLanguage from "./components/SwitchLanguage";
import { useTranslation } from "react-i18next";
import Menu from "./Menu.js";
import { useEffect, useState } from "react";

function App() {
  const { t } = useTranslation();
  const [isLoggedIn, setIsloggedIn] = useState(false);
  

  return (
    <Router>
      <Switch>
        <Route path="/registration">
          <div>{t("registration")}</div>
          <SwitchLanguage />
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
    </Router>
  );
}

export default App;
