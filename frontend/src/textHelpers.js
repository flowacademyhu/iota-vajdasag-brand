/*
 * Removes all accents from words and makes them uppercase.
 **/
const normalize = (word) => {
  return word
    .normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '')
    .toUpperCase()
}

const highlightTableProps = ['name', 'city', 'address', 'category', 'ownerName']

export { normalize, highlightTableProps }
