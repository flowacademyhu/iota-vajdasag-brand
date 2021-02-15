import React from "react";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import MenuComponent from "../components/MenuComponent";

const menuItems = [
  {
    path: "users",
    title: "users"
  },
  {
    path: "products",
    title: "products"
  },
  {
    path: "events",
    title: "events"
  },
  {
    path: "logout",
    title: "logout"
  }
];

export default function Menu() {
  const { t } = useTranslation();
  return (
    <div className="d-flex" id="wrapper">
      <div className="bg-light border-right" id="sidebar-wrapper">
        <div className="list-group list-group-flush">
          {menuItems.map((item, index) => (
            <div key={index} className="nav-item">
              <Link
                activeClassName="active"
                className="nav-link"
                to="/super-admin/users"
              >
                <MenuComponent title={t(item.title)}></MenuComponent>
              </Link>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}