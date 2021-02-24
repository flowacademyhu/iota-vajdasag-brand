import React from 'react'
import { useTranslation } from 'react-i18next'
import { Search } from 'react-bootstrap-icons'

const Searchbar = ({ setSearchKeyword }) => {
  const { t } = useTranslation()

  return (
    <nav className="navbar-toggler navbar-light bg-light">
      <form className="form-inline">
        <input
          className="searchbar form-control mr-sm-2"
          type="search"
          placeholder={t('search')}
          aria-label={t('search')}
          onChange={(e) => setSearchKeyword(e.target.value)}
        />
        <Search></Search>
      </form>
    </nav>
  )
}

export default Searchbar
