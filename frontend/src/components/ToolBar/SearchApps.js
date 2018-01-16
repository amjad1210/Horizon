import React, { PureComponent } from 'react'

import _ from 'lodash'

import { Search } from 'semantic-ui-react'

const source = {}

class SearchApps extends PureComponent {

  componentWillMount() {
    this.resetComponent()
  }

  resetComponent = () => this.setState({ isLoading: false, results: [], value: '' })

  handleSearchChange = (e, { value }) => {
    this.setState({ isLoading: true, value })

    setTimeout(() => {
      if (this.state.value.length < 1) return this.resetComponent()

      const re = new RegExp(_.escapeRegExp(this.state.value), 'i')
      const isMatch = result => re.test(result.name)

      this.setState({
        isLoading: false,
        results: _.filter(source, isMatch),
      })
    }, 500)
  }

  render() {
    const { isLoading, value, results } = this.state

    return (
      <Search
        size='mini'
        placeholder='Search...'
        loading={isLoading}
        onSearchChange={this.handleSearchChange}
        results={results}
        value={value}
        {...this.props}
      />
    )
  }
}

export default SearchApps
