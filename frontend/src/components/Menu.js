import React from 'react'
import { useTranslation } from 'react-i18next'
import MenuComponent from '../components/MenuComponent'

const menuItems = [
  {
    path: '/super-admin/users',
    title: 'menu.users',
  },
  {
    path: '/super-admin/products',
    title: 'menu.products',
  },
  {
    path: '/super-admin/events',
    title: 'menu.events',
  },
  {
    path: 'logout',
    title: 'menu.signout',
  },
]

export default function Menu() {
  const { t } = useTranslation()
  return (
    <div className="d-flex" id="wrapper">
      <div className="bg-light border-right" id="sidebar-wrapper">
        <div className="list-group list-group-flush">
          {menuItems.map((item, index) => (
            <MenuComponent
              key={index}
              title={t(item.title)}
              path={item.path}
            ></MenuComponent>
          ))}
        </div>
      </div>
    </div>
  )
}
