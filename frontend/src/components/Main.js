import Login from '../pages/Login'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect, Link
} from 'react-router-dom'
import SwitchLanguage from '../components/SwitchLanguage'
import React, { useContext } from 'react'
import Menu from '../components/Menu'
import Home from '../pages/Home'
import { TokenContext } from '../TokenContext'
import Logout from './logout'
import Registration from '../pages/Registration'
import JusoftLogo from '../media/jusoftdark1.png'
import ShowLoggedUserData from '../components/ShowLoggedUserData'
import ForgottenPassword from '../pages/ForgottenPassword'

const Main = () => {
  const { token } = useContext(TokenContext)

  return (
    <>
      <Router>
        <div className="container-fluid m-0 header">
          <div className="row">
            <div className="col-5">
              <Link exact to="/home">
                <img src={JusoftLogo} alt="Jusoft logo" />
              </Link>
            </div>
            <div className="col-4 d-flex">
              <ShowLoggedUserData />
            </div>
            <div className="col-3 d-flex">
              <SwitchLanguage />
            </div>
          </div>
        </div>
        <div className="container-fluid p-0">
          <div className="row">
            {token && (
              <div className="col-3">
                <Menu />
              </div>
            )}
            {token ? (
              <Switch>
                <Route path="/home">
                  <div className="col-9">
                    <Home />
                  </div>
                </Route>
                <Route path="/logout">
                  <Logout />
                </Route>
              </Switch>
            ) : (
                <>
                  <Switch>
                    <Route path="/login">
                      <Login />
                    </Route>
                    <Route path="/registration">
                      <Registration />
                    </Route>
                    <Route path="/forgottenpassword">
                      <ForgottenPassword />
                    </Route>
                    <Redirect to="/login" />
                  </Switch>
                </>
              )}
          </div>
        </div>
      </Router>
    </>
  )
}

export default Main
