while (<>) {
	print if /\([^\(\)]*\w+[^\)\(]*\)/
}
