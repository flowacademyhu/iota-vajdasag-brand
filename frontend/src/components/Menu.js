import React from "react";
import { NavLink } from "react-router-dom";

const menuItems = [
  {
    path: "users",
    title: "menu.users"
  },
  {
    path: "products",
    title: "menu.products"
  },
  {
    path: "events",
    title: "menu.events"
  },
  {
    path: "logout",
    title: "menu.signout"
  }
];

export default function Menu() {
  return (
    <div class="d-flex" id="wrapper">
      <div class="bg-light border-right" id="sidebar-wrapper">
        <div className="list-group list-group-flush">
          {menuItems.map((item, index) => (
            <div key={index} className="nav-item">
              <NavLink
                activeClassName="active"
                className="nav-link"
                to={`/${item.path}`}
              >
                {item.title}
              </NavLink>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}