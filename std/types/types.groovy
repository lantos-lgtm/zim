Bit //@magic 1 0

parseBool: Function {
    args: {
        self: String
    }
    return: ResultWithError {
        self: Bit
    }
    body: Body {
        if (value) {
            in: Vector {
                String ("1"),
                String ("true"),
                String ("True"),
                String ("TRUE"),
            }
            body: Body {
                return ResultWithError(Bit (1)),
            },
            else: Body {
                return(error: "Invalid boolean value"),
            },
        }
        if (value) {
            in: Vector {
                String ("0"),
                String ("false"),
                String ("False"),
                String ("FALSE"),
            },
            body: Body {
                return(Bit(0),)
            },
            else: Body {
                return(
                    self: value
                    error: "Invalid boolean value"
                )
            },
        }
    }
}
parseBool: Function {
    args: {
        self: String
    }
    return: ResultWithError {
        self: Bit
    }
    body: Body {
        if {
            value: self
            is: Int(1)
            body: Body {
                return(Bit (1))
            }
        }
        if {
            value: self
            is: Int(0)
            body: Body {
                return(Bit (0))
            }
        }
        return(error: "Invalid boolean value")
    }
}

Boolean: Type {
    self: Bit
    parse: Function {
        self: Type
        return: ResultWithError {
            self: Bool
        }
        body: Body {
            if {
                value: self.type
                is: Vector {
                    {
                        value: String
                        body: parseBool(self.value)
                    }
                    {
                        value: Int
                        body: parseBool(self.value)
                    }
                    {
                        value: Bit
                        body: Body {
                            return(self.value)
                        }
                    }
                }
            }
        }
    }
}

Byte: Type {
    data: Vector {
        self: Bit
        size 8
    }
}

Char: Byte

IntType: Type {
    size: Vector {
        self: Bit
        size 8
    }
    signed: Boolean
}

IntBase: {
    intType: IntType
    data: Vector {
        self: { Bit }
        size: { intType.size }
    }
}

intRange: Vector {8 16 32 64 128 256 }
@createInts {
    fn: Function {
        body: Body { 
            var size loop {
            on: intRange
            fn: Function {
                body: Body {
                    var signed loop {
                        on: Boolean.fieldPairs()
                        fn: Function {
                            prefix: String
                            if (signed) {
                                body: Body {
                                    signed = String("U")
                                }
                            }
                            Struct {
                                name: String.format`${prefix}Int${String.parse(size)}`
                                self: IntBase {
                                        intType: IntType {
                                            size: Int.parse(size)
                                            signed: Boolean.parse(signed)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@createInts{}

FloatType: Type {
    size: Vector {
        self: Bit
        size 8
    }
    signed: Boolean
}

Float: Type {
    floatType: FloatType
    data: Vector {
        self: { Bit }
        size: { size }
    }
}

String: Type {
    length Int
    max: Int
    data: Vector {
        self: {Char}
    }
}