import "./App.css";
import { Switch, Route, Link } from "react-router-dom";

function App() {
  return (
    <Switch>
      <Route path="/registration">
        <div>Registration component</div>
      </Route>
      <Route path="/companyAdmin">
        <div>Company</div>
      </Route>
      <Route path="/superAdmin">
        <div>Superadmin</div>
      </Route>
      <Route path="/login">
        <div>Login</div>
      </Route>
    </Switch>
  );
}

export default App;
