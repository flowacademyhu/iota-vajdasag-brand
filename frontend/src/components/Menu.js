import React from 'react'
import { useTranslation } from 'react-i18next'
import MenuComponent from './MenuComponent'
import { People, Newspaper, Shop, ArrowBarLeft } from 'react-bootstrap-icons'

const menuItems = [
  {
    path: '/super-admin/users',
    title: 'menu.users',
    icon: People,
  },
  {
    path: '/super-admin/products',
    title: 'menu.products',
    icon: Shop,
  },
  {
    path: '/super-admin/events',
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
  const { t } = useTranslation()

  return (
    <div className="border-right">
      <div className="list-group">
        {menuItems.map((item, index) => (
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
