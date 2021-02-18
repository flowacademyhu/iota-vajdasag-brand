import React from 'react'
import { useTranslation } from 'react-i18next'

const SwitchLanguage = () => {
  const { i18n } = useTranslation()

  const actualLanguage = i18n.language

  const handleChange = (e) => {
    i18n.changeLanguage(e.target.value)
  }

  return (
    <div className="col-3 d-flex justify-content-center switchLanguage">
      <select
        className="form-select m-4"
        value={actualLanguage}
        onChange={handleChange}
      >
        <option value="hu">🇭🇺 Magyar</option>
        <option value="sr">🇷🇸 Српски</option>
        <option value="en">🇬🇧 English</option>
      </select>
    </div>
  )
}

export default SwitchLanguage
