while (<>) {
	s/\([^\)]*\)/\(\)/g;
	print;
}
