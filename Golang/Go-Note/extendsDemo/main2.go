package main2

type A struct {
	Name string
	age  int
}
type B struct {
	Name  string
	Score float64
}
type C struct {
	A // 匿名結構體
	B
}
type D struct {
	a A // 有名結構體
}

type Goods struct {
	Name string
	Price float64
}

type Brand struct {
	Name string
	Address string
}

type TV struct {
	Goods
	Brand
}

func main() {
	var c C
	// c.Name = "tom" // error，就近原則 C 沒有 Name 字段，因此需指定 Name 字段是誰的
	c.A.Name = "tom"

	var d D
	d.a.Name = "Jack"

	tv := TV{
		Goods{
			Name: "TV1",
			Price: 5000.2,
		},
		Brand{
			Name: "itachi",
			Address: "12345678",
		}
	}
}
