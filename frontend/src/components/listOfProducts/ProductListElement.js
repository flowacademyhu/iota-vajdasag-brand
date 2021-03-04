import React from 'react'
import Highlighter from 'react-highlight-words'
import DeleteProductButton from './DeleteProductButton'
import EditProductButton from './EditProductButton'
import { normalize, highlightableProps } from '../../textHelpers'

const ProductListElement = ({ product, searchKeyword, fullList }) => {
  return (
    <>
      <tr>
        {Object.keys(product)
          .filter((key) => highlightableProps.includes(key))
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
        <td>{product.name}</td>
        <td>{product.address}</td>
        <td>{product.city}</td>
        <td>
          {product.subcategory
            ? product.category + ': ' + product.subcategory
            : product.category}
        </td>
        {fullList && <td>{product.ownerName}</td>}
        <td>
          <DeleteProductButton productId={product.id} />
          <EditProductButton productId={product.id} />
        </td>
      </tr>
    </>
  )
}

export default ProductListElement
