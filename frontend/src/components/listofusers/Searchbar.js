import React from "react";
import { useTranslation } from "react-i18next";

const Searchbar = ({ setSearchKeyword }) => {
  const { t } = useTranslation();

  return (
    <nav className="navbar-toggler navbar-light bg-light">
      <form className="form-inline">
        <input
          className="searchbar form-control mr-sm-2"
          type="search"
          placeholder={t("Search")}
          aria-label="Search"
          onChange={(e) => setSearchKeyword(e.target.value)}
        />
        <i className="bi bi-search"></i>
      </form>
    </nav>
  );
};

export default Searchbar;
