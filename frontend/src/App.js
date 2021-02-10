import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Registration from "./components/Registration";

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/components/Registration">
          <div>
            <Registration/>
          </div>
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
