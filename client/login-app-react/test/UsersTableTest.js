import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import UsersTable from '../src/js/UsersTable'

describe('UsersTable', () => {
  it('displays the component', () => {
    let users = [
      {id: 1, username: 'adam', role: 'staff'},
      {id: 2, username: 'bob', role: 'staff'},
      {id: 3, username: 'cam', role: 'admin'},
      {id: 4, username: 'dan', role: 'admin'}
    ]

    const usersTable = shallow(<UsersTable users={users}/>)

    expect(usersTable.find('.adam').contains(<td>adam</td>)).toBe(true)
    expect(usersTable.find('.adam').contains(<td>staff</td>)).toBe(true)
    expect(usersTable.find('.bob').contains(<td>bob</td>)).toBe(true)
    expect(usersTable.find('.bob').contains(<td>staff</td>)).toBe(true)
    expect(usersTable.find('.cam').contains(<td>cam</td>)).toBe(true)
    expect(usersTable.find('.cam').contains(<td>admin</td>)).toBe(true)
    expect(usersTable.find('.dan').contains(<td>dan</td>)).toBe(true)
    expect(usersTable.find('.dan').contains(<td>admin</td>)).toBe(true)
  })
})
