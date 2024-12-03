pub fn day3() {
    let part1_result = day3_part1(INPUT);
    println!("Day 3, Part 1: {}", part1_result);
    let part2_result = day3_part2(INPUT);
    println!("Day 3, Part 2: {}", part2_result);
}

fn day3_part1(input: &str) -> i32 {
    parse_input(input)
        .iter()
        .map(|(a, b)| a * b)
        .sum()
}

fn day3_part2(input: &str) -> i32 {
    0
}

fn parse_input(input: &str) -> Vec<(i32, i32)> {
    let r = regex::Regex::new(r"mul\(\d+,\d+\)").unwrap();
    r.find_iter(input).map(|m| m.as_str().split(","))
    .map(|mut x| {
        let a = x.next().unwrap().strip_prefix("mul(").unwrap().parse().unwrap();
        let b = x.next().unwrap().strip_suffix(")").unwrap().parse().unwrap();
        (a, b)
    }).collect()
}

#[cfg(test)]
mod tests {
    use super::*;

    const TEST_INPUT: &str = include_str!("test_input.txt");

    #[test]
    fn test_day3_part1() {
        assert_eq!(day3_part1(TEST_INPUT), 161);
    }

    #[test]
    fn test_day3_part2() {
        assert_eq!(day3_part2(TEST_INPUT), 0);
    }
}

const INPUT: &str = include_str!("input.txt");