import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import UsersTable from '../src/js/UsersTable'

describe('UsersTable', () => {
  it('displays the usernames', () => {
    let users = [
      {id: 1, username: 'adam', role: 'staff'},
      {id: 2, username: 'bob', role: 'staff'},
      {id: 3, username: 'cam', role: 'admin'}
    ]

    const usersTable = shallow(<UsersTable users={users}/>)

    let link1 = usersTable.find('Link.adam')
    expect(link1.length).toBe(1)
    expect(link1.node.props.to).toBe('users/adam')

    let link2 = usersTable.find('Link.bob')
    expect(link2.length).toBe(1)
    expect(link2.node.props.to).toBe('users/bob')

    let link3 = usersTable.find('Link.cam')
    expect(link3.length).toBe(1)
    expect(link3.node.props.to).toBe('users/cam')
  })

  it('displays roles', () => {
    let users = [
      {id: 1, username: 'adam', role: 'staff'},
      {id: 2, username: 'bob', role: 'staff'},
      {id: 3, username: 'cam', role: 'admin'}
    ]

    const usersTable = shallow(<UsersTable users={users}/>)

    expect(usersTable.find('.adam').contains(<td>staff</td>)).toBe(true)
    expect(usersTable.find('.bob').contains(<td>staff</td>)).toBe(true)
    expect(usersTable.find('.cam').contains(<td>admin</td>)).toBe(true)
  })
})
