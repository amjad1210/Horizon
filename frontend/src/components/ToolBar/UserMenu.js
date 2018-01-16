import React, { PureComponent } from 'react'

import { Menu } from 'semantic-ui-react'

class UserMenu extends PureComponent {
  render() {
    return (
      <Menu.Item icon='user' className='user'
        name='Amjad'
      />
    )
  }

}

export default UserMenu
