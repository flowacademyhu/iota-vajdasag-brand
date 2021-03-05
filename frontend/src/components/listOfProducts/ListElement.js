import React from 'react'
import Highlighter from 'react-highlight-words'
import DeleteProductButton from './DeleteProductButton'
import EditProductButton from './EditProductButton'
import AddEventButton from '../listofevents/AddEventButton'
import EventsProductButton from '../listofevents/EventsProductButton'
import { normalize, highlightableProps } from '../../textHelpers'

const ListElement = ({ product, searchKeyword }) => {
  return (
    <tr>
      {Object.keys(product)
        .filter((key) => highlightableProps.includes(key))
        .map((key) => (
          <td key={key}>
            <Highlighter
              highlightClassName="search-found-word"
              searchWords={[searchKeyword]}
              textToHighlight={product[key]}
              autoEscape={true}
              caseSensitive={false}
              sanitize={(word) => normalize(word)}
            />
          </td>
        ))}
      <td>
        <DeleteProductButton productId={product.id} />
        <EditProductButton productId={product.id} />
        <AddEventButton productId={product.id} />
        <EventsProductButton productId={product.id} />
      </td>
    </tr>
  )
}

export default ListElement
