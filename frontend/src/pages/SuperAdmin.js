import React from "react";
import { useTranslation } from "react-i18next";
import { useParams, Switch, Route, useRouteMatch, BrowserRouter as Router } from "react-router-dom";
import UsersList from '../components/UsersList';

const SuperAdmin = () => {
   const {t} = useTranslation();

    return (
        <>
            <Route exact path="/super-admin">
                <h1>{t("welcome")}</h1>
            </Route>
            <Route path="/super-admin/users">
                <UsersList />
            </Route>
        </>
    );
}

export default SuperAdmin