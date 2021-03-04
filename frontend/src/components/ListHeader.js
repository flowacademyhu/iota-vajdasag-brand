import React from 'react'
import { useTranslation } from 'react-i18next'
import HeaderCell from './HeaderCell'

const ListHeader = ({
  onColumnClick,
  isSortAscending,
  sortKey,
  headerCellNames,
}) => {
  const { t } = useTranslation()

  return (
    <thead>
      <tr>
        {headerCellNames.map((cellname) => (
          <HeaderCell
            cellname={cellname}
            onColumnClick={onColumnClick}
            isSortAscending={isSortAscending}
            sortKey={sortKey}
            key={cellname}
          />
        ))}
        <th scope="col">{t('headerCell.actions')}</th>
      </tr>
    </thead>
  )
}

export default ListHeader
