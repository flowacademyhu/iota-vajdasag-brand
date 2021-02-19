import React from 'react'
import { NavLink } from 'react-router-dom'

const MenuComponent = ({ title, path, Icon }) => {
  return (
    <NavLink className="nav-link" to={path}>
      <h4>
        <Icon /> {title}
      </h4>
    </NavLink>
  )
}

export default MenuComponent
