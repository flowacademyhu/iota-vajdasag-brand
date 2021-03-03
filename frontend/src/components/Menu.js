import React, { useState } from 'react'
import { useTranslation } from 'react-i18next'
import MenuComponent from './MenuComponent'
import { People, Newspaper, Shop, ArrowBarLeft } from 'react-bootstrap-icons'
import useLoggedInUser from "../hooks/useLoggedInUser";

const menuItemsSuperAdmin = [
  {
    path: '/users',
    title: 'menu.users',
    icon: People,
  },
  {
    path: '/items',
    title: 'menu.items',
    icon: Shop,
  },
  {
    path: 'events',
    title: 'menu.events',
    icon: Newspaper,
  },
  {
    path: 'logout',
    title: 'menu.signout',
    icon: ArrowBarLeft,
  },
]

const menuItemsCompanyAdmin = [
  {
    path: '/items',
    title: 'menu.items',
    icon: Shop,
  },
  {
    path: '/events',
    title: 'menu.events',
    icon: Newspaper,
  },
  {
    path: 'logout',
    title: 'menu.signout',
    icon: ArrowBarLeft,
  },
]

const Menu = () => {
  const userRole = useLoggedInUser().role
  console.log(userRole)
  const { t } = useTranslation()

  return (
    <div className="border-right">
      <div className="list-group">
        {userRole === "CegAdmin" && menuItemsCompanyAdmin.map((item, index) => (
          <MenuComponent
            key={index}
            Icon={item.icon}
            title={t(item.title)}
            path={item.path}
          />
        ))}
        {userRole === "SuperAdmin" && menuItemsSuperAdmin.map((item, index) => (
          <MenuComponent
            key={index}
            Icon={item.icon}
            title={t(item.title)}
            path={item.path}
          />
        ))}
      </div>
    </div>
  )
}

export default Menu
