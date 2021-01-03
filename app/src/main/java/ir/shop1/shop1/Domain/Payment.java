package ir.shop1.shop1.Domain;

import java.util.List;

public class Payment  {

    /**
     * Message : 0
     * factor : {"Id":2121,"Buyer":"محسن","Address":"ذررردذتت ذت","Mobile":"09132243308","PhoneNumber":null,"Date":"2020-12-26T10:03:14.1224946+03:30","FactorItems":[{"Id":1075,"Qty":1,"ProductName":"طراحی شبکه های عصبی","UnitPrice":3200,"Product":{"Id":133,"Name":"طراحی شبکه های عصبی","Qty":10,"Desc":"برق - کامپیوتر","Publisher":"دانشگاه آزاد واحد نجف آباد","Author":"مارتین نی-هاگان-هواردبی-دموت-مارک بیل-ترجمه علی ناظران-مهدی شانه","Year":"1388","Published":"اول","Editor":"0","ISBN":"97889642235025","Thumbnail":"/Upload/Products/d15d14530630a04fbc08e41096af8f893fd1.jpg","Price":3200,"RealPrice":"3,200","SepratePrice":"3,200","Discount":0,"Main_Image":"/Upload/Products/3072ab8805e1404a26098830607eae7fc1d5.jpg","Images":null,"Tags":null,"Like":0,"TotalVotes":0,"Status":true,"TotalComment":0,"Category":{"Id":32,"Name":"کامپیوتر","Image":"/Upload/Categories/034ab96c09b0604690082f406ac39f0b2cd8.png","Thumbnail":"/Upload/Categories/28d2c1c80c111048430b824056e43c031417.png","Products":[],"Children":null,"Parent":null},"Comments":[]}}],"UserId":87,"User":{"Id":87,"Email":"No Email","Password":"No Pass","Fullname":"محسن","Status":true,"PhoneNumber":"03134491508","Mobile":"09132243308","PostalCode":"0985665888","Address":"ذررردذتت ذت","LinkStatus":true,"Api_Token":"fe32b5020006e04d7808d0009f96206c5ba5","Role":null},"TotalPrice":3200,"Status":false,"IsAdminShow":false,"PostalCode":"0985665888","City_Id":0,"TransportationFee":0,"Discount_Code":null,"Discount_Amount":0}
     */

    private int Message;
    private Factor factor;

    public int getMessage() {
        return Message;
    }

    public void setMessage(int Message) {
        this.Message = Message;
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    public static class Factor {
        /**
         * Id : 2121
         * Buyer : محسن
         * Address : ذررردذتت ذت
         * Mobile : 09132243308
         * PhoneNumber : null
         * Date : 2020-12-26T10:03:14.1224946+03:30
         * FactorItems : [{"Id":1075,"Qty":1,"ProductName":"طراحی شبکه های عصبی","UnitPrice":3200,"Product":{"Id":133,"Name":"طراحی شبکه های عصبی","Qty":10,"Desc":"برق - کامپیوتر","Publisher":"دانشگاه آزاد واحد نجف آباد","Author":"مارتین نی-هاگان-هواردبی-دموت-مارک بیل-ترجمه علی ناظران-مهدی شانه","Year":"1388","Published":"اول","Editor":"0","ISBN":"97889642235025","Thumbnail":"/Upload/Products/d15d14530630a04fbc08e41096af8f893fd1.jpg","Price":3200,"RealPrice":"3,200","SepratePrice":"3,200","Discount":0,"Main_Image":"/Upload/Products/3072ab8805e1404a26098830607eae7fc1d5.jpg","Images":null,"Tags":null,"Like":0,"TotalVotes":0,"Status":true,"TotalComment":0,"Category":{"Id":32,"Name":"کامپیوتر","Image":"/Upload/Categories/034ab96c09b0604690082f406ac39f0b2cd8.png","Thumbnail":"/Upload/Categories/28d2c1c80c111048430b824056e43c031417.png","Products":[],"Children":null,"Parent":null},"Comments":[]}}]
         * UserId : 87
         * User : {"Id":87,"Email":"No Email","Password":"No Pass","Fullname":"محسن","Status":true,"PhoneNumber":"03134491508","Mobile":"09132243308","PostalCode":"0985665888","Address":"ذررردذتت ذت","LinkStatus":true,"Api_Token":"fe32b5020006e04d7808d0009f96206c5ba5","Role":null}
         * TotalPrice : 3200
         * Status : false
         * IsAdminShow : false
         * PostalCode : 0985665888
         * City_Id : 0
         * TransportationFee : 0
         * Discount_Code : null
         * Discount_Amount : 0
         */

        private int Id;
        private String Buyer;
        private String Address;
        private String Mobile;
        private Object PhoneNumber;
        private String Date;
        private int UserId;
        private User User;
        private int TotalPrice;
        private boolean Status;
        private boolean IsAdminShow;
        private String PostalCode;
        private int City_Id;
        private int TransportationFee;
        private Object Discount_Code;
        private int Discount_Amount;
        private List<FactorItems> FactorItems;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getBuyer() {
            return Buyer;
        }

        public void setBuyer(String Buyer) {
            this.Buyer = Buyer;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public Object getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(Object PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public User getUser() {
            return User;
        }

        public void setUser(User User) {
            this.User = User;
        }

        public int getTotalPrice() {
            return TotalPrice;
        }

        public void setTotalPrice(int TotalPrice) {
            this.TotalPrice = TotalPrice;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean Status) {
            this.Status = Status;
        }

        public boolean isIsAdminShow() {
            return IsAdminShow;
        }

        public void setIsAdminShow(boolean IsAdminShow) {
            this.IsAdminShow = IsAdminShow;
        }

        public String getPostalCode() {
            return PostalCode;
        }

        public void setPostalCode(String PostalCode) {
            this.PostalCode = PostalCode;
        }

        public int getCity_Id() {
            return City_Id;
        }

        public void setCity_Id(int City_Id) {
            this.City_Id = City_Id;
        }

        public int getTransportationFee() {
            return TransportationFee;
        }

        public void setTransportationFee(int TransportationFee) {
            this.TransportationFee = TransportationFee;
        }

        public Object getDiscount_Code() {
            return Discount_Code;
        }

        public void setDiscount_Code(Object Discount_Code) {
            this.Discount_Code = Discount_Code;
        }

        public int getDiscount_Amount() {
            return Discount_Amount;
        }

        public void setDiscount_Amount(int Discount_Amount) {
            this.Discount_Amount = Discount_Amount;
        }

        public List<FactorItems> getFactorItems() {
            return FactorItems;
        }

        public void setFactorItems(List<FactorItems> FactorItems) {
            this.FactorItems = FactorItems;
        }

        public static class User {
            /**
             * Id : 87
             * Email : No Email
             * Password : No Pass
             * Fullname : محسن
             * Status : true
             * PhoneNumber : 03134491508
             * Mobile : 09132243308
             * PostalCode : 0985665888
             * Address : ذررردذتت ذت
             * LinkStatus : true
             * Api_Token : fe32b5020006e04d7808d0009f96206c5ba5
             * Role : null
             */

            private int Id;
            private String Email;
            private String Password;
            private String Fullname;
            private boolean Status;
            private String PhoneNumber;
            private String Mobile;
            private String PostalCode;
            private String Address;
            private boolean LinkStatus;
            private String Api_Token;
            private Object Role;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getEmail() {
                return Email;
            }

            public void setEmail(String Email) {
                this.Email = Email;
            }

            public String getPassword() {
                return Password;
            }

            public void setPassword(String Password) {
                this.Password = Password;
            }

            public String getFullname() {
                return Fullname;
            }

            public void setFullname(String Fullname) {
                this.Fullname = Fullname;
            }

            public boolean isStatus() {
                return Status;
            }

            public void setStatus(boolean Status) {
                this.Status = Status;
            }

            public String getPhoneNumber() {
                return PhoneNumber;
            }

            public void setPhoneNumber(String PhoneNumber) {
                this.PhoneNumber = PhoneNumber;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public String getPostalCode() {
                return PostalCode;
            }

            public void setPostalCode(String PostalCode) {
                this.PostalCode = PostalCode;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public boolean isLinkStatus() {
                return LinkStatus;
            }

            public void setLinkStatus(boolean LinkStatus) {
                this.LinkStatus = LinkStatus;
            }

            public String getApi_Token() {
                return Api_Token;
            }

            public void setApi_Token(String Api_Token) {
                this.Api_Token = Api_Token;
            }

            public Object getRole() {
                return Role;
            }

            public void setRole(Object Role) {
                this.Role = Role;
            }
        }

        public static class FactorItems {
            /**
             * Id : 1075
             * Qty : 1
             * ProductName : طراحی شبکه های عصبی
             * UnitPrice : 3200
             * Product : {"Id":133,"Name":"طراحی شبکه های عصبی","Qty":10,"Desc":"برق - کامپیوتر","Publisher":"دانشگاه آزاد واحد نجف آباد","Author":"مارتین نی-هاگان-هواردبی-دموت-مارک بیل-ترجمه علی ناظران-مهدی شانه","Year":"1388","Published":"اول","Editor":"0","ISBN":"97889642235025","Thumbnail":"/Upload/Products/d15d14530630a04fbc08e41096af8f893fd1.jpg","Price":3200,"RealPrice":"3,200","SepratePrice":"3,200","Discount":0,"Main_Image":"/Upload/Products/3072ab8805e1404a26098830607eae7fc1d5.jpg","Images":null,"Tags":null,"Like":0,"TotalVotes":0,"Status":true,"TotalComment":0,"Category":{"Id":32,"Name":"کامپیوتر","Image":"/Upload/Categories/034ab96c09b0604690082f406ac39f0b2cd8.png","Thumbnail":"/Upload/Categories/28d2c1c80c111048430b824056e43c031417.png","Products":[],"Children":null,"Parent":null},"Comments":[]}
             */

            private int Id;
            private int Qty;
            private String ProductName;
            private int UnitPrice;
            private Product Product;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getQty() {
                return Qty;
            }

            public void setQty(int Qty) {
                this.Qty = Qty;
            }

            public String getProductName() {
                return ProductName;
            }

            public void setProductName(String ProductName) {
                this.ProductName = ProductName;
            }

            public int getUnitPrice() {
                return UnitPrice;
            }

            public void setUnitPrice(int UnitPrice) {
                this.UnitPrice = UnitPrice;
            }

            public Product getProduct() {
                return Product;
            }

            public void setProduct(Product Product) {
                this.Product = Product;
            }

            public static class Product {
                /**
                 * Id : 133
                 * Name : طراحی شبکه های عصبی
                 * Qty : 10
                 * Desc : برق - کامپیوتر
                 * Publisher : دانشگاه آزاد واحد نجف آباد
                 * Author : مارتین نی-هاگان-هواردبی-دموت-مارک بیل-ترجمه علی ناظران-مهدی شانه
                 * Year : 1388
                 * Published : اول
                 * Editor : 0
                 * ISBN : 97889642235025
                 * Thumbnail : /Upload/Products/d15d14530630a04fbc08e41096af8f893fd1.jpg
                 * Price : 3200
                 * RealPrice : 3,200
                 * SepratePrice : 3,200
                 * Discount : 0
                 * Main_Image : /Upload/Products/3072ab8805e1404a26098830607eae7fc1d5.jpg
                 * Images : null
                 * Tags : null
                 * Like : 0
                 * TotalVotes : 0
                 * Status : true
                 * TotalComment : 0
                 * Category : {"Id":32,"Name":"کامپیوتر","Image":"/Upload/Categories/034ab96c09b0604690082f406ac39f0b2cd8.png","Thumbnail":"/Upload/Categories/28d2c1c80c111048430b824056e43c031417.png","Products":[],"Children":null,"Parent":null}
                 * Comments : []
                 */

                private int Id;
                private String Name;
                private int Qty;
                private String Desc;
                private String Publisher;
                private String Author;
                private String Year;
                private String Published;
                private String Editor;
                private String ISBN;
                private String Thumbnail;
                private int Price;
                private String RealPrice;
                private String SepratePrice;
                private int Discount;
                private String Main_Image;
                private Object Images;
                private Object Tags;
                private int Like;
                private int TotalVotes;
                private boolean Status;
                private int TotalComment;
                private Category Category;
                private List<?> Comments;

                public int getId() {
                    return Id;
                }

                public void setId(int Id) {
                    this.Id = Id;
                }

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public int getQty() {
                    return Qty;
                }

                public void setQty(int Qty) {
                    this.Qty = Qty;
                }

                public String getDesc() {
                    return Desc;
                }

                public void setDesc(String Desc) {
                    this.Desc = Desc;
                }

                public String getPublisher() {
                    return Publisher;
                }

                public void setPublisher(String Publisher) {
                    this.Publisher = Publisher;
                }

                public String getAuthor() {
                    return Author;
                }

                public void setAuthor(String Author) {
                    this.Author = Author;
                }

                public String getYear() {
                    return Year;
                }

                public void setYear(String Year) {
                    this.Year = Year;
                }

                public String getPublished() {
                    return Published;
                }

                public void setPublished(String Published) {
                    this.Published = Published;
                }

                public String getEditor() {
                    return Editor;
                }

                public void setEditor(String Editor) {
                    this.Editor = Editor;
                }

                public String getISBN() {
                    return ISBN;
                }

                public void setISBN(String ISBN) {
                    this.ISBN = ISBN;
                }

                public String getThumbnail() {
                    return Thumbnail;
                }

                public void setThumbnail(String Thumbnail) {
                    this.Thumbnail = Thumbnail;
                }

                public int getPrice() {
                    return Price;
                }

                public void setPrice(int Price) {
                    this.Price = Price;
                }

                public String getRealPrice() {
                    return RealPrice;
                }

                public void setRealPrice(String RealPrice) {
                    this.RealPrice = RealPrice;
                }

                public String getSepratePrice() {
                    return SepratePrice;
                }

                public void setSepratePrice(String SepratePrice) {
                    this.SepratePrice = SepratePrice;
                }

                public int getDiscount() {
                    return Discount;
                }

                public void setDiscount(int Discount) {
                    this.Discount = Discount;
                }

                public String getMain_Image() {
                    return Main_Image;
                }

                public void setMain_Image(String Main_Image) {
                    this.Main_Image = Main_Image;
                }

                public Object getImages() {
                    return Images;
                }

                public void setImages(Object Images) {
                    this.Images = Images;
                }

                public Object getTags() {
                    return Tags;
                }

                public void setTags(Object Tags) {
                    this.Tags = Tags;
                }

                public int getLike() {
                    return Like;
                }

                public void setLike(int Like) {
                    this.Like = Like;
                }

                public int getTotalVotes() {
                    return TotalVotes;
                }

                public void setTotalVotes(int TotalVotes) {
                    this.TotalVotes = TotalVotes;
                }

                public boolean isStatus() {
                    return Status;
                }

                public void setStatus(boolean Status) {
                    this.Status = Status;
                }

                public int getTotalComment() {
                    return TotalComment;
                }

                public void setTotalComment(int TotalComment) {
                    this.TotalComment = TotalComment;
                }

                public Category getCategory() {
                    return Category;
                }

                public void setCategory(Category Category) {
                    this.Category = Category;
                }

                public List<?> getComments() {
                    return Comments;
                }

                public void setComments(List<?> Comments) {
                    this.Comments = Comments;
                }

                public static class Category {
                    /**
                     * Id : 32
                     * Name : کامپیوتر
                     * Image : /Upload/Categories/034ab96c09b0604690082f406ac39f0b2cd8.png
                     * Thumbnail : /Upload/Categories/28d2c1c80c111048430b824056e43c031417.png
                     * Products : []
                     * Children : null
                     * Parent : null
                     */

                    private int Id;
                    private String Name;
                    private String Image;
                    private String Thumbnail;
                    private Object Children;
                    private Object Parent;
                    private List<?> Products;

                    public int getId() {
                        return Id;
                    }

                    public void setId(int Id) {
                        this.Id = Id;
                    }

                    public String getName() {
                        return Name;
                    }

                    public void setName(String Name) {
                        this.Name = Name;
                    }

                    public String getImage() {
                        return Image;
                    }

                    public void setImage(String Image) {
                        this.Image = Image;
                    }

                    public String getThumbnail() {
                        return Thumbnail;
                    }

                    public void setThumbnail(String Thumbnail) {
                        this.Thumbnail = Thumbnail;
                    }

                    public Object getChildren() {
                        return Children;
                    }

                    public void setChildren(Object Children) {
                        this.Children = Children;
                    }

                    public Object getParent() {
                        return Parent;
                    }

                    public void setParent(Object Parent) {
                        this.Parent = Parent;
                    }

                    public List<?> getProducts() {
                        return Products;
                    }

                    public void setProducts(List<?> Products) {
                        this.Products = Products;
                    }
                }
            }
        }
    }
}
