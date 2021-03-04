import React from 'react'
import { useTranslation } from 'react-i18next'
import SortingSign from './SortingSign'

const HeaderCell = ({ onColumnClick, isSortAscending, sortKey, cellname }) => {
  const { t } = useTranslation()

  return (
    <th scope="col" onClick={() => onColumnClick(cellname)}>
      {t(`headerCell.${cellname}`)}
      <SortingSign
        value={cellname}
        sortKey={sortKey}
        isSortAscending={isSortAscending}
      />
    </th>
  )
}

export default HeaderCell
