import React from 'react'
import Highlighter from 'react-highlight-words'
import DeleteProductButton from './DeleteProductButton'
import EditProductButton from './EditProductButton'
import {
  normalize,
  highlightTableProps,
} from '../frequentlyUsedFunctionsAndVariables'

const highlightTableProps = ['name', 'city', 'address', 'category', 'ownerName']

const ListElement = ({ product, searchKeyword }) => {
  return (
    <>
      <tr>
        {Object.keys(product)
          .filter((key) => highlightTableProps.includes(key))
          .map((key) => (
            <td>
              <Highlighter
                highlightClassName="search-found-word"
                searchWords={[searchKeyword]}
                textToHighlight={product[key]}
                autoEscape={true}
                caseSensitive={false}
                sanitize={(word) => normalize(word)}
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
