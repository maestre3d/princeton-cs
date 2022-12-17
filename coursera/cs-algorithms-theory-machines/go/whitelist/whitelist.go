package whitelist

import (
	"bufio"
	"io"
)

type Whitelist interface {
	IsAllowed(v string) bool
}

type WhitelistSet struct {
	list map[string]struct{}
}

var _ Whitelist = &WhitelistSet{}

func NewWhitelistSet(reader io.Reader) *WhitelistSet {
	list := make(map[string]struct{})
	scanner := bufio.NewScanner(reader)
	for scanner.Scan() {
		ln := scanner.Text()
		list[ln] = struct{}{}
	}
	return &WhitelistSet{
		list: list,
	}
}

func (w WhitelistSet) IsAllowed(v string) bool {
	_, ok := w.list[v]
	return ok
}
