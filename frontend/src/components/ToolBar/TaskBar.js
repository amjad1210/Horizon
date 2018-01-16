import React, { PureComponent } from 'react'

import { Menu } from 'semantic-ui-react'

class TaskBar extends PureComponent {
  render() {
    return (
      <Menu.Menu className='taskbar'>
        <Menu.Item active icon='server'
          name='Nodes'
        />
        <Menu.Item icon='wifi'
          name='Network'
        />
      </Menu.Menu>
    )
  }
}

export default TaskBar
