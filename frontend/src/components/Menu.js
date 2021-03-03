import React from 'react'
import { useTranslation } from 'react-i18next'
import MenuComponent from './MenuComponent'
import { People, Newspaper, Shop, ArrowBarLeft } from 'react-bootstrap-icons'
import useLoggedInUser from "../hooks/useLoggedInUser";

const menuItemsSuperAdmin = [
  {
    path: "/home/users",
    title: 'menu.users',
    icon: People,
  },
  {
    path: '/home/items',
    title: 'menu.items',
    icon: Shop,
  },
  {
    path: '/home/events',
    title: 'menu.events',
    icon: Newspaper,
  },
  {
    path: '/logout',
    title: 'menu.signout',
    icon: ArrowBarLeft,
  },
]

const menuItemsCompanyAdmin = [
  {
    path: '/home/items',
    title: 'menu.items',
    icon: Shop,
  },
  {
    path: '/home/events',
    title: '/home/menu.events',
    icon: Newspaper,
  },
  {
    path: '/logout',
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
