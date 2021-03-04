import React from 'react';

const ListElements = ({ event }) => {
    return (
        <tr>
            <td>{event.name}</td>
            <td>{event.eventstart}</td>
            <td>{event.eventend}</td>
            <td>{event.place}</td>
            <td></td>
        </tr>
    );
}

export default ListElements;