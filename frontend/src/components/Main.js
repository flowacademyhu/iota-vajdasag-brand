import Login from "../pages/Login";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import SwitchLanguage from "../components/SwitchLanguage";
import { useTranslation } from "react-i18next";
import React, { useContext } from 'react';
import Menu from "../components/Menu";
import SuperAdmin from "../pages/SuperAdmin";
import { TokenContext } from "../TokenContext";
import Logout from "./logout";


const Main = () => {
    const { token } = useContext(TokenContext);
    const { t } = useTranslation();
    const loggedInAsSuperAdmin = false;
    const loggedInAsCompanyAdmin = false;
    return (
      <>
        <SwitchLanguage />
        <Router>
          <div className="container">
            <div className="row">
              {token &&
                (<div className="col-3">
                  <Menu />
                </div>)}
              {token ? <Redirect to="/super-admin" /> : <Redirect to="/login" />}
              {loggedInAsCompanyAdmin && <Redirect to="/company-admin" />}
              {loggedInAsSuperAdmin && <Redirect to="/super-admin" />}
              <div className="col">
                <Switch>
                  <Route path="/registration">
                    <div>{t("Registration")}</div>
                  </Route>
                  <Route path="/login">
                    <Login />
                  </Route>
                  <Route path="/company-admin">
                    <div>{t("companyAdmin")}</div>
                  </Route>
                  <Route path="/super-admin">
                    <SuperAdmin />
                  </Route>
                  <Route path="/logout">
                    <Logout/>
                  </Route>
                </Switch>
              </div>
            </div>
          </div>
        </Router>
      </>
    );
  }

  export default Main