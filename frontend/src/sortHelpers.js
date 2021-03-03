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

export { sortColumn }
