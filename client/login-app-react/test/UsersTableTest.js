import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import UsersTable from '../src/js/UsersTable'

describe('UsersTable', () => {
  it('displays the component', () => {
    let users = [
      {id: '1', name: 'adam'},
      {id: '2', name: 'bob'},
      {id: '3', name: 'cam'},
      {id: '4', name: 'dan'}
    ]

    const userList = shallow(<UsersTable users={users}/>)

    expect(userList.contains(<tr><td>1</td><td>adam</td></tr>)).toBe(true)
    expect(userList.contains(<tr><td>2</td><td>bob</td></tr>)).toBe(true)
    expect(userList.contains(<tr><td>3</td><td>cam</td></tr>)).toBe(true)
    expect(userList.contains(<tr><td>4</td><td>dan</td></tr>)).toBe(true)
  })
})
