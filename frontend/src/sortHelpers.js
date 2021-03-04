const headerCellNamesUsers = [
  'full_name',
  'email',
  'enabled',
  'registeredAt',
  'id',
]
const headerCellNamesItems = [
  'name',
  'address',
  'city',
  'category',
  'ownerName',
]

const sortColumn = (a, b, sortKey, isSortAscending) => {
  if (sortKey === '') {
    return 0
  }

  if (isSortAscending) {
    return a[sortKey] > b[sortKey] ? 1 : -1
  } else {
    return a[sortKey] < b[sortKey] ? 1 : -1
  }
}

export { sortColumn, headerCellNamesUsers, headerCellNamesItems }
