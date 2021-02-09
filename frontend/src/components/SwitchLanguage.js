import React from "react";
import { useTranslation } from "react-i18next";

const SwitchLanguage = () => {
  const { i18n } = useTranslation();

  return (
    <div className="col-2">
      <select
        className="form-select mb-3"
        onClick={(e) => i18n.changeLanguage(e.target.value)}
      >
        <option value="hu" selected>
          🇭🇺 Magyar
        </option>
        <option value="sr">🇷🇸 Српски</option>
        <option value="en">🇬🇧 English</option>
      </select>
    </div>
  );
};

export default SwitchLanguage;
