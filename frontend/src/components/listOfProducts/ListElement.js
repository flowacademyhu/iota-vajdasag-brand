import React from 'react'
import Highlighter from 'react-highlight-words'
import DeleteProductButton from './DeleteProductButton'
import EditProductButton from './EditProductButton'
import "../frequentlyUsedFunctions"

const ListElement = ({ product, searchKeyword }) => {
  return (
    <>
      <tr>
        {Object.entries(product)
          .filter(
            ([key, value]) =>
              key === 'name' ||
              key === 'city' ||
              key === 'address' ||
              key === 'category' ||
              key === 'owner'
          )
          .map(([key, value]) => (
            <td>
              <Highlighter
                highlightClassName="searchFoundWord"
                searchWords={[searchKeyword]}
                textToHighlight={value}
                autoEscape={true}
                caseSensitive={false}
                sanitize={(word) => removeAccentsFromWords(word)}
                key={key}
              />
            </td>
          ))}
        <td>
          <DeleteProductButton productId={product.id} />
          <EditProductButton productId={product.id} />
        </td>
      </tr>
    </>
  )
}

export default ListElement
