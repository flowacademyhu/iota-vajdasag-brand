import React from "react";
import { NavLink } from "react-router-dom";

const items = ["menu.users", "menu.products", "menu.events", "menu.logout"];

export default function Menu() {
    return (
      <div className="d-flex" id="wrapper">
        <div className="bg-light border-right" id="sidebar-wrapper">
          <div className="list-group list-group-flush">
            <div key={items[0]} className="nav-item">
              <NavLink
                activeClassName="active"
                className="nav-link"
                to={`/users`}
              >
                {items[0]}
              </NavLink>
            </div>
            <div key={items[1]} className="nav-item">
              <NavLink
                activeClassName="active"
                className="nav-link"
                to={`/products`}
              >
                {items[1]}
              </NavLink>
            </div>
            <div key={items[2]} className="nav-item">
              <NavLink
                activeClassName="active"
                className="nav-link"
                to={`/events`}
              >
                {items[2]}
              </NavLink>
            </div>
            <div key={items[3]} className="nav-item">
              <NavLink
                activeClassName="active"
                className="nav-link"
                to={`/logout`}
              >
                {items[3]}
              </NavLink>
            </div>
          </div>
        </div>
      </div>
    );
  }