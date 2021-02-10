import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Menu from "./components/Menu";

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/registration">
          <div>Registration component</div>
        </Route>
        <Route path="/company-admin">
          <Menu />
          <div id="page-content-wrapper">Company</div>
        </Route>
        <Route path="/super-admin">
        <Menu />          
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
