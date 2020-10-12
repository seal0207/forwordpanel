sed -n '/^net.ipv4.ip_forward=1/'p /etc/sysctl.conf | grep -q "net.ipv4.ip_forward=1";if [ $? -ne 0 ]; then echo -e "net.ipv4.ip_forward=1" >> /etc/sysctl.conf && sysctl -p;fi
arr1=(`iptables -L FORWARD -n  --line-number |grep "REJECT"|grep "0.0.0.0/0"|sort -r|awk '{print $1,$2,$5}'|tr " " ":"|tr "\n" " "`);for cell in ${arr1[@]};do arr2=(`echo $cell|tr ":" " "`);index=${arr2[0]};echo 删除禁止FOWARD的规则$index;iptables -D FORWARD $index;done
iptables --policy FORWARD ACCEPT
