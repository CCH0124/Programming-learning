package main

import "errors"

import "fmt"

type Account struct {
	AccountNo string
	pwd       string
	Balance   float64
}

// 存款
func (account *Account) Deposite(money float64, pwd string) error {
	if pwd != account.pwd {
		return errors.New("password error")
	}
	if money <= 0 {
		return errors.New("input money error")
	}

	account.Balance += money
	fmt.Println("save money success")
	return nil
}

// 取款
func (account *Account) WithDraw(money float64, pwd string) error {
	if pwd != account.pwd {
		return errors.New("password error")
	}
	if money <= 0 || money > account.Balance {
		return errors.New("input money error")
	}
	account.Balance -= money
	fmt.Println("get money success")
	return nil
}

// 查詢餘額
func (account *Account) Quary(pwd string) error {
	if pwd != account.pwd {
		return errors.New("password error")
	}

	fmt.Println("Account : ", account.AccountNo, " 餘額 : ", account.Balance)
	return nil
}

func main() {
	account := Account{
		AccountNo: "t111",
		pwd:       "123456",
		Balance:   1000.0,
	}
	account.Quary("123456")
}
