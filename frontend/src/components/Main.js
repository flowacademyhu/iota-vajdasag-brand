import Login from '../pages/Login'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from 'react-router-dom'
import SwitchLanguage from '../components/SwitchLanguage'
import { useTranslation } from 'react-i18next'
import React, { useContext, useState } from 'react'
import Menu from '../components/Menu'
import Home from '../pages/Home'
import { TokenContext } from '../TokenContext'
import Logout from './logout'
import Registration from '../pages/Registration'
import JusoftLogo from '../media/jusoftdark1.png'
import ShowLoggedUserData from '../components/ShowLoggedUserData'
import ForgettenPassword from '../pages/ForgottenPassword'
import useLoggedInUser from "../hooks/useLoggedInUser";

const Main = () => {
  const { token } = useContext(TokenContext)
  const { t } = useTranslation()
  const userRole = useLoggedInUser().role

  return (
    <>
      <div className="container-fluid m-0 header">
        <div className="row">
          <div className="col-5">
            <img src={JusoftLogo} alt="Jusoft logo" />
          </div>
          <div className="col-4 d-flex">
            <ShowLoggedUserData />
          </div>
          <div className="col-3 d-flex">
            <SwitchLanguage />
          </div>
        </div>
      </div>
      <Router>
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
                      <Home/>
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
                      <ForgettenPassword />
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
