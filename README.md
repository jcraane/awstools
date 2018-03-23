# AWS tools

Repository containing various tools when working with AWS.

## Functionality

**RiUtilizationCalculator.kt**

Calculates the total normalization factor for all EC2 instance types running in the account for which the script is run. These normalization factors can be used to determine how many and which reserved instances should be purchased. See [How RI's are applied](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/apply_ri.html).