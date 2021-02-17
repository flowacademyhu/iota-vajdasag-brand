<<<<<<< HEAD
import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/registration">
          <div>Registration component</div>
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
=======
import React from 'react';
import { TokenProvider } from "./TokenContext";
import Main from "./components/Main";




export default function App() {
  return (
    <>
      <TokenProvider>
        <Main></Main>
      </TokenProvider>
    </>
>>>>>>> master
  );
}

export default App;
