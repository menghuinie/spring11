import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const GroupList = () => {

  const [groups, setGroups] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('api/groups')
      .then(response => response.json())
      .then(data => {
        setGroups(data);
        setLoading(false);
      })
  }, []);    // The second argument ([] an empty array) is optional. useEffect(<function>, <dependency>)

 
/* Fetch is the modern replacement for XMLHttpRequest: unlike XMLHttpRequest, which uses callbacks, Fetch is promise-based and is integrated with features of the modern web such as service workers and Cross-Origin Resource Sharing (CORS).
With the Fetch API, you make a request by calling fetch(), which is available as a global function in both window and worker contexts. 
You pass it a Request object or a string containing the URL to fetch, along with an optional argument to configure the request.
The fetch() function returns a Promise which is fulfilled with a Response object representing the server's response. */

  const remove = async (id) => {
    await fetch(`/api/group/${id}`, {  // to make asynchronous HTTP requests.
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedGroups = [...groups].filter(i => i.id !== id);
      setGroups(updatedGroups);
    });
  }

  if (loading) {   //Conditional Rendering: The component returns a loading msg if loading is true, OR the actual data once loading is complete.
    return <p>Loading...</p>;
  }

  const groupList = groups.map(group => {
    const address = `${group.address || ''} ${group.city || ''} ${group.stateOrProvince || ''}`;
    return <tr key={group.id}>
      <td style={{whiteSpace: 'nowrap'}}>{group.name}</td>
      <td>{address}</td>
      <td>{group.events.map(event => {
        return <div key={event.id}>{new Intl.DateTimeFormat('en-US', {
          year: 'numeric',
          month: 'long',
          day: '2-digit'
        }).format(new Date(event.date))}: {event.title}</div>
      })}</td>
      <td>
        <ButtonGroup>
          <Button size="sm" color="primary" tag={Link} to={"/groups/" + group.id}>Edit</Button>
          <Button size="sm" color="danger" onClick={() => remove(group.id)}>Delete</Button>
        </ButtonGroup>
      </td>
    </tr>
  });

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
        <div className="float-end">
          <Button color="success" tag={Link} to="/groups/new">Add Group</Button>
        </div>
        <h3>The Tour</h3>
        <Table className="mt-4">
          <thead>
          <tr>
            <th width="20%">Name</th>
            <th width="20%">Location</th>
            <th>Events</th>
            <th width="10%">Actions</th>
          </tr>
          </thead>
          <tbody>
          {groupList}
          </tbody>
        </Table>
      </Container>
    </div>
  );
};

export default GroupList;