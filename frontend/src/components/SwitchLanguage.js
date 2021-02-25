import React from 'react'
import { useTranslation } from 'react-i18next'

const SwitchLanguage = () => {
  const { i18n } = useTranslation()

  const actualLanguage = i18n.language

  const handleChange = (e) => {
    i18n.changeLanguage(e.target.value)
  }

  return (
    <select
      className="form-select m-4"
      value={actualLanguage}
      onChange={handleChange}
    >
      <option value="hu">🇭🇺 Magyar</option>
      <option value="sr">🇷🇸 Srpski</option>
      <option value="en">🇬🇧 English</option>
    </select>
  )
}

export default SwitchLanguage
