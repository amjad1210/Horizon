import React, { PureComponent } from 'react'

import { Menu } from 'semantic-ui-react'

class ToggleExpose extends PureComponent {
  state = { isExposed: false }

  handleToggle = () => {
    this.setState({ isExposed: !this.state.isExposed })
  }

  render() {
    const { isExposed } = this.state

    return (
      <Menu.Item icon={isExposed ? 'object ungroup' : 'object group'} onClick={this.handleToggle}/>
    )
  }
}

export default ToggleExpose
