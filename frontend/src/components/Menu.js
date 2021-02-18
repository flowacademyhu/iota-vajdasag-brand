import React from 'react'
import { useTranslation } from 'react-i18next'
import { NavLink } from 'react-router-dom'
import { People, Newspaper, Shop, ArrowBarLeft } from 'react-bootstrap-icons'

const Menu = () => {
  const { t } = useTranslation()

  return (
    <div className="border-right">
      <div className="list-group">
        <NavLink className="nav-link" to="/super-admin/users">
          <h4>
            <People /> {t('menu.users')}
          </h4>
        </NavLink>
        <NavLink className="nav-link" to="/super-admin/products">
          <h4>
            <Shop /> {t('menu.products')}
          </h4>
        </NavLink>
        <NavLink className="nav-link" to="/super-admin/events">
          <h4>
            <Newspaper /> {t('menu.events')}
          </h4>
        </NavLink>
        <NavLink
          className="nav-link"
          activeClassName="activeNavLink"
          to="logout"
        >
          <h4>
            <ArrowBarLeft /> {t('menu.signout')}
          </h4>
        </NavLink>
      </div>
    </div>
  )
}

export default Menu
