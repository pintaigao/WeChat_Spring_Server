package main

import "fmt"

func main() {
	name := "Todd McLeod"

	tpl := `
		<html lang = "en">
		<head>
		<title> Hello World!</title>
		</head>
		<body>
		<h1> ` + name + `</h1>
		</body>
		</html>`

	fmt.Println(tpl)
}
